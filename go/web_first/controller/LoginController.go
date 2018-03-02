package controller

import (
	"net/http"
	"fmt"
	"html/template"
)

func login(w http.ResponseWriter, r *http.Request) {
	fmt.Println("login-------------start")
	//请求的是登陆数据，那么执行登陆的逻辑判断
	//表单数据必须先执行ParseForm方法后才能使用r.Form[""]取值
	//如果不想使用r.Form[""]也可以使用r.FormValue(""),这个方法会在取值的时候自己调用ParseForm，
	r.ParseForm()
	//r.FormValue("username")
	s := r.Form.Get("username")
	if s == "" {
		fmt.Println("username is nil")
	}
	fmt.Println("username:", r.Form["username"])
	vs := r.Form["password"]
	fmt.Println("password:", vs)
	template.HTMLEscape(w, []byte(vs[0]))
	fmt.Println("login------------------------end")
}

func init() {
	http.HandleFunc("/login", login)
}
