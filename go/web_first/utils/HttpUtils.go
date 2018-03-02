package utils

import (
	"fmt"
	"net/http"
	"io/ioutil"
	"bytes"
	"mime/multipart"
	"os"
	"io"
)

func test() {
	//这个fileName是提交的表单中域filename，不是文件路径filename
	fileName, targetUrl := "111.jpg", "http://localhost:8000/upload"
	PostFile(fileName, targetUrl)
}

func PostFile(fileName, targetUrl string) error {
	//这个fileName是提交的表单中域filename，不是文件路径filename
	bodyBuf := &bytes.Buffer{}
	bodyWiter := multipart.NewWriter(bodyBuf)
	fileWriter, e := bodyWiter.CreateFormFile("uploadfile", fileName)
	//bodyWiter.WriteField("otherfiedname","value")
	if e != nil {
		fmt.Println("error")
		return e
	}
	fh, e := os.Open("D:/" + fileName)
	if e != nil {
		fmt.Println("error2")
		return e
	}
	_, err := io.Copy(fileWriter, fh)
	if err != nil {
		fmt.Println("error3")
		return e
	}
	contentType := bodyWiter.FormDataContentType()
	bodyWiter.Close()

	resp, err := http.Post(targetUrl, contentType, bodyBuf)
	if err != nil {
		fmt.Println("error4")
		return e
	}
	defer resp.Body.Close()
	fmt.Println(resp.Status)
	respBody, e := ioutil.ReadAll(resp.Body)
	if e != nil {
		fmt.Println("error5")
		return e
	}
	fmt.Println(string(respBody))
	return nil
}
