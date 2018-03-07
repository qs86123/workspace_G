package controllers

import (
	"github.com/astaxie/beego"
	"github.com/astaxie/beego/orm"
	"beego_learn/models"
	"fmt"
	"encoding/json"
)

type UserController struct {
	beego.Controller
}

func (this *UserController) Save() {
	user := models.User{}
	json.Unmarshal(this.Ctx.Input.RequestBody, &user)
	ormer := orm.NewOrm()
	i, e := ormer.Insert(&user)
	if e != nil {
		fmt.Println(e)
	}
	fmt.Println(i)
	this.Ctx.WriteString("ok")
}

func (this *UserController) Info() {
	s := this.GetString("name")
	ormer := orm.NewOrm()
	var user models.User
	ormer.QueryTable("user").Filter("name", s).One(&user)
	this.Data["json"] = &user
	this.ServeJSON()
}
