package com.txl.demo.version;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by TeaBee on 2017/8/9.
 */
public class ApiVesrsionCondition implements RequestCondition<ApiVesrsionCondition> {

    // 版本格式， 这里用 /x.y/的形式
    public final static Pattern VERSION_PATTERN = Pattern.compile("[1-9].[0-9]");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ApiVersion apiVersion;

    /**
     *     配置的版本号 大于 请求版本号
     *     如果某个版本向下有可用的版本号，则用向下最接近的版本号。
     *     如果向下没有可用的版本号，则用向上最接近的版本号。
     */
    private ThreadLocal<Boolean> notMatchVersion = new ThreadLocal<Boolean>();

    public ApiVesrsionCondition(ApiVersion apiVersion) {
        this.apiVersion = apiVersion;
    }

    public ApiVesrsionCondition combine(ApiVesrsionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVesrsionCondition(other.getApiVersion());
    }

    public ApiVesrsionCondition getMatchingCondition(HttpServletRequest request) {
        if (!checkVersionCanUsed()){
            return null;
        }

        String versionHeader = request.getHeader("goufan_api_version");
        //没有传入version, 则默认匹配,而且用最初的版本。
        if (versionHeader == null){
            notMatchVersion.set(true);
            return this;
        }

        Matcher m = VERSION_PATTERN.matcher(versionHeader);
        //版本号格式错误，则不匹配
        if(!m.matches()){
            return null;
        }

        double version = Double.valueOf(versionHeader);
        if(version < this.apiVersion.value()) // 如果请求的版本号大于配置版本号， 则满足
        {
            notMatchVersion.set(true);
        }
        return this;
    }

    /**
     * 检查版本是否可以
     * 1 .是否制定版本启用
     * 2. 时间已经过期
     * @return
     */
    private boolean checkVersionCanUsed() {
        if (!apiVersion.isUsing()){
            return false;
        }

        String expireDateStr = apiVersion.expireDate();
        Date nowDate = new Date();
        Date expireDate;
        try {
            expireDate = DATE_FORMAT.parse(expireDateStr);
            //现在时间晚于 过期时间，则不匹配
            if (nowDate.after(expireDate)){
                return false;
            }
        }
        //时间格式错误，则匹配。
        catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int compareTo(ApiVesrsionCondition other, HttpServletRequest request) {
        Boolean otherNotMatchVersion = other.getNotMatchVersion();
        Boolean thisNotMatchVersion = this.getNotMatchVersion();

        //2个 Condiction 都没有匹配到版本，则版本号更小的优先
        if (otherNotMatchVersion && thisNotMatchVersion){
            return other.getApiVersion().value() - this.apiVersion.value() < 0 ? 1 : -1;
        }
        //匹配到版本的，更优先
        else if (otherNotMatchVersion){
            return -1;
        }
        //匹配到版本的，更优先
        else if (thisNotMatchVersion){
            return 1;
        }
        //都匹配到版本的，则版本号更大的优先
        else {
            return other.getApiVersion().value() - this.apiVersion.value() > 0 ? 1 : -1;
        }

    }

    public ApiVersion getApiVersion() {
        return apiVersion;
    }

    public Boolean getNotMatchVersion() {
        return notMatchVersion.get() == null ? false : notMatchVersion.get();
    }
}