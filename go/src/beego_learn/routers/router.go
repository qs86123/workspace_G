package routers

import (
	"beego_learn/controllers"
	"github.com/astaxie/beego"
)

func init() {
	beego.Router("/", &controllers.MainController{})
	beego.Router("/test", &controllers.HtmlTestController{})
	beego.Router("/json", &controllers.JsonController{})
	//*是所有http method都可以访问改方法，但是是最后匹配，也就是说当http Method找到匹配的方法后就不会去匹配*了，
	//如下：如果post请求，首先匹配到了Save方法，就不会再去匹配AllFunc方法了
	beego.Router("/method", &controllers.MethodController{}, "*:AllFunc;post:Save;get:Info")
	beego.Router("/user", &controllers.UserController{}, "post:Save;get:Info")
}
