package com.stla.figure.controller;

import com.stla.figure.utility.CheckAuthorizeCode;
import com.stla.figure.utility.EncoderFile;
import com.stla.figure.utility.LicenseCode;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class ApiController {

    //行政区划，替代物理地址，加密只用了硬盘序号
    public String xzqh = "100000";

    //系统名称
    public String xm_name = "ZNXDPRO";

    //授权码文件保存位置
    private String licensePath = "e:/encoder.txt";


    /**
     * 单独的验证授权的方法，该方法中拿授权文件中的授权码，解密成一个
     * 系统名称+行政区划+硬盘序号+授权时间+授权截止时间串，
     * 然后获取本机的系统名称+行政区划+硬盘序号串，两者进行比较，如果验证通过，再判断时间是否
     * 到期，如果都通过，则返回验证通过。
     * 否则，使用本机的系统名称+行政区划+硬盘序号串，加密成一个申请码返回给前台
     * @return
     */
    @CrossOrigin
    @GetMapping(value= "/license" )
    public Object license(){
        Map resultMap = new HashMap();
        try {
            if (CheckAuthorizeCode.AuthorizeCode(licensePath,xm_name, xzqh)) {
                resultMap.put("success",true);
                resultMap.put("msg","授权成功");
                return resultMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("success",false);
        resultMap.put("msg","系统未授权，请授权后登录");
        resultMap.put("license", LicenseCode.getApplyCode(xm_name,xzqh));
        return resultMap;
    }

    /**
     * 单独的授权码保存接口，拿到传入的授权码后，保存到服务器的一个文件中
     * @param code
     * @return
     */
    @CrossOrigin
    @GetMapping(value= "/importlicense/{code}" )
    public Object importlicense(@PathVariable String code){
        Map resultMap = new HashMap();
        File file = new File(licensePath);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        EncoderFile.mywrite(file,code);
        boolean flag = false;
        try {
            flag = CheckAuthorizeCode.AuthorizeCode(licensePath,xm_name, xzqh); //验证授权码是否正确
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){ //授权成功
            resultMap.put("success",true);
            resultMap.put("msg","授权成功");
            return resultMap;
        }
        resultMap.put("success",false);
        resultMap.put("msg","授权码错误");
        return resultMap;
    }

}
