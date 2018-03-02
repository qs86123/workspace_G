package myhandler

import (
	"net/http"
	"fmt"
	"strings"
	"html/template"
)

type MyMux struct {
}

func (p *MyMux) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Println("mymux", r.URL.Path)
	if strings.Contains(r.URL.Path, ".html") {
		PageHandler{}.ServeHTTP(w, r)
		return
	}
	fmt.Println("handle by default handler")
	http.DefaultServeMux.ServeHTTP(w, r)
	return
}

type PageHandler struct {
}

func (p PageHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	s := r.RequestURI
	fmt.Println("handle by pagehandler.ServeHTTP-----", s)
	files, e := template.ParseFiles("html" + s)
	if e != nil {
		fmt.Println(e)
	}
	files.Execute(w, nil)
}
