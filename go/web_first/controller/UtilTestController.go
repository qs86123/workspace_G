package controller

import (
	"net/http"
	"web_first/utils"
	"fmt"
)

func HttpPostUtilTest(w http.ResponseWriter, r *http.Request) {
	fmt.Println("upload by http")
	//这个fileName是提交的表单中域filename，不是文件路径filename
	fileName, targetUrl := "111.jpg", "http://localhost:8000/upload"
	e := utils.PostFile(fileName, targetUrl)
	if e != nil {
		fmt.Println(e)
		w.Write([]byte(`{"msg":"file upload fail","error":"1"}`))
	}
	w.Write([]byte(`{"msg":"file upload success","error":"0"}`))
}

func init() {
	http.HandleFunc("/uploadByHttp", HttpPostUtilTest)
}
