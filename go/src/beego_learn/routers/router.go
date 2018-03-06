package routers

import (
	"beego_learn/controllers"
	"github.com/astaxie/beego"
)

func init() {
    beego.Router("/", &controllers.MainController{beego.Controller{TplExt:"html"}})
}
