package controller

import (
	"net/http"
	"fmt"
)

func dataHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Println("coming ...")
	w.Write([]byte("{\"name\":\"this is data\"}"))
}

func init() {
	http.HandleFunc("/data", dataHandler)
}
