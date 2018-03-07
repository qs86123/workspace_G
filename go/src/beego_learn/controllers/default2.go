package controllers

import (
	"github.com/astaxie/beego"
)

type HtmlTestController struct {
	beego.Controller
}

func (c *HtmlTestController) Get() {
	c.TplName = "main.html"
}
