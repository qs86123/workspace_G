package cn.itcast.ssm.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// shiro的认证是通过FormAuthenticationFilter中取出username和password进行验证，所以我们在它验证之前进行验证码的校验

		// 取出session中的正确的验证码
		HttpServletRequest req = (HttpServletRequest) request;
		String validateCode = (String) req.getSession().getAttribute("validateCode");

		// 取出用户从页面输入的验证码
		String randomCode = req.getParameter("randomcode");
		if (randomCode != null && !randomCode.equals(validateCode)) {
			// 如果验证失败，将验证码信息设置到request中
			req.setAttribute("shiroLoginFailure", "randomCodeError");
			// 返回true，拒绝访问，不再验证用户名和密码
			return true;
		}
		return super.onAccessDenied(request, response);
	}

}
