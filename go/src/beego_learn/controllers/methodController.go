package controllers

import (
	"github.com/astaxie/beego"
	"fmt"
)

type MethodController struct {
	beego.Controller
}

func (c *MethodController) Save() {
	fmt.Println("post:this is <save> method")
	c.Ctx.WriteString("post:this is <save> method")
}

func (c *MethodController) Update() {
	fmt.Println("post:this is <update> method")
	c.Ctx.WriteString("post:this is <update> method")
}

func (c *MethodController) Info() {
	fmt.Println("get:this is <info> method")
	c.Ctx.WriteString("get:this is <info> method")
}

func (c *MethodController) AllFunc() {
	fmt.Println("*:this is <AllFunc> method")
	c.Ctx.WriteString("*:this is <AllFunc> method")
}
