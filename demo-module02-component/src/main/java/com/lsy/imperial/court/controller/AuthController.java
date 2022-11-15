package com.lsy.imperial.court.controller;

import com.lsy.imperial.court.entity.Emp;
import com.lsy.imperial.court.service.api.EmpService;
import com.lsy.imperial.court.util.ImperialCourtConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private EmpService empService;

    /**
     *
     * @param loginAccount 登录账号
     * @param loginPassword 登录密码
     * @param session   会话
     * @param model 模型
     * @return
     */
    @RequestMapping("/auth/login")
    public String doLogin(
            @RequestParam("loginAccount")String loginAccount,
            @RequestParam("loginPassword")String loginPassword,
            HttpSession session,
            Model model
    ) {
        // 尝试通过账号密码查询用户
        Emp emp = empService.getEmpByLogin(loginAccount, loginPassword);

        // 判定登录是否成功
        if (emp == null) {
            // 失败
            // 将失败提示信息存入 message
            model.addAttribute("message", ImperialCourtConst.LOGIN_FAILED_MESSAGE);
            //跳转至 index
            return "index";
        }

        // 成功
        // 将用户信息存入 session 域中
        session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME, emp);
        //跳转至 target
        return "target";

    }

}
