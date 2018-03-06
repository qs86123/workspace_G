package main

import (
	"fmt"
	"reflect"
	"image"
	"image/color"
	"image/draw"
	"golang.org/x/tour/pic"
)

func main() {
	fmt.Println("----------------------1--------------------------")
	reflect1()
	fmt.Println("----------------------2--------------------------")
	reflect2()
	fmt.Println("----------------------3--------------------------")
	reflect3()
	fmt.Println("----------------------4--------------------------")
	reflect4()
	react()
}

func reflect1() {
	var x float64 = 3.4
	fmt.Println("type:", reflect.TypeOf(x))
	fmt.Println("value:", reflect.ValueOf(x))
	v := reflect.ValueOf(x)
	fmt.Println(v.Type())
	fmt.Println(v.Kind() == reflect.Float64)
	fmt.Println(v.Float())
	fmt.Printf("value is %7.1e\n", v.Interface())
	f := v.Interface().(float64)
	fmt.Println(f)
}

func reflect2() {
	type MyInt int
	var xx MyInt = 7
	fmt.Println("type:", reflect.TypeOf(xx))
	fmt.Println(reflect.ValueOf(xx).Kind() == reflect.Int)
	fmt.Println("value:", reflect.ValueOf(xx))
}

func reflect3() {
	var xxx float64 = 3.4
	vvv := reflect.ValueOf(xxx)
	fmt.Println("vvv canset:", vvv.CanSet())
	vvvaddr := reflect.ValueOf(&xxx)
	fmt.Println("vvvaddr canset:", vvvaddr.CanSet())
	//vvvelem := vvv.Elem()
	//fmt.Println("vvvelem canset:",vvvelem.CanSet())
	vvvaddrelem := vvvaddr.Elem()
	fmt.Println("vvvelem canset:", vvvaddrelem.CanSet())
	vvvaddrelem.SetFloat(7.4)
	fmt.Println(xxx)
}

func reflect4() {
	type T struct {
		A int `this is tag for field A`
		B string
	}
	t := T{23, "wt"}
	s := reflect.ValueOf(&t).Elem()
	fmt.Println(s.CanSet(), s.Type().NumField())
	typeOfT := s.Type()
	for i := 0; i < s.NumField(); i++ {
		f := s.Field(i)
		fmt.Printf("%d: %s %s = %v %v %v\n", i,
			typeOfT.Field(i).Name, f.Type(), f.Interface(), f.CanSet(),typeOfT.Field(i).Tag)
	}
	s.Field(0).SetInt(24)
	s.Field(1).SetString("wtt")
	fmt.Println(t)
}

func react()  {

	m := image.NewRGBA(image.Rect(0, 0, 640, 480))
	pic.ShowImage(m)
	blue := color.RGBA{0, 0, 255, 255}
	draw.Draw(m, m.Bounds(), &image.Uniform{blue}, image.ZP, draw.Src)          // prints true
	pic.ShowImage(m)
}

