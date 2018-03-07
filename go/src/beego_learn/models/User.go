package models

import "github.com/astaxie/beego/orm"

type User struct {
	Id   string `orm:"pk"`
	Name string
	Age  int
}

func init() {
	orm.RegisterModel(new(User))
}
