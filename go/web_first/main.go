package main

import (
	"net/http"
	"log"
	"web_first/myhandler"
	_ "web_first/controller"
	"fmt"
	"html/template"
)

//该方式也可以合并到MyMux的ServeHTTP路径处理中
//这个方法其实是DefaultMux处理的，在我们自己的MyMux中对根路径/并没有进行处理，就调用了DefaultMux来处理
func loginHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Println("loginHandler...login.html", r.URL.Path)
	files, e := template.ParseFiles("html/login.html")
	if e != nil {
		fmt.Println(e)
	}
	files.Execute(w, nil)
	return
}

func main() {
	http.Handle("/template/", http.StripPrefix("/template/", http.FileServer(http.Dir("./template"))))
	http.HandleFunc("/", loginHandler)
	err := http.ListenAndServe(":8000", &myhandler.MyMux{})
	if err != nil {
		log.Fatal("ListenAndServe: ", err.Error())
	}
}
