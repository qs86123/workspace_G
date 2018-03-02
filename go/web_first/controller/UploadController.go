package controller

import (
	"net/http"
	"fmt"
	"os"
	"io"
)

func upload(w http.ResponseWriter, r *http.Request) {
	fmt.Println("upload------------") //获取请求的方法
	r.ParseMultipartForm(32 << 20)
	file, handler, err := r.FormFile("uploadfile")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()
	fmt.Fprintf(w, "%v", handler.Header)
	f, err := os.OpenFile("D:/test/"+handler.Filename, os.O_WRONLY|os.O_CREATE, 0666)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer f.Close()
	io.Copy(f, file)
}

func init() {
	http.HandleFunc("/upload", upload)
}
