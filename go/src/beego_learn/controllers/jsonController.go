package controllers

import (
	"github.com/astaxie/beego"
	"encoding/xml"
)

type MyJson struct {
	XMLName xml.Name `xml:"XMLName"`
	Name    string   `json:"nameForJson" xml:"nameForXml"`
	Age     int64    `json:"ageForJson" xml:"ageForXml"`
	//小写字母开头不导出
	sex string `json:"sexForJson" xml:"sexForXml"`
}

type JsonController struct {
	beego.Controller
}

func (c *JsonController) Get() {
	c.Data["json"] = `{"name":"wt","age":"24"}`
	c.Ctx.WriteString(c.Data["json"].(string))
}

func (c *JsonController) Post() {
	c.Data["json"] = &MyJson{Name: "wt", Age: 23, sex: "male"}
	c.ServeJSON()
}

func (c *JsonController) Delete() {
	c.Data["xml"] = &MyJson{Name: "wt", Age: 23, sex: "male"}
	c.ServeXML()
}
