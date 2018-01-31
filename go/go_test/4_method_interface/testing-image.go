package main

import (
	"golang.org/x/tour/pic"
	"image"
	"image/color"
)

type Image struct {
	Width, Height int
}

func (img Image) Bounds() image.Rectangle {
	return image.Rect(0, 0, img.Width, img.Height)
}
func (img Image) ColorModel() color.Model {
	return color.RGBAModel
}
func (img Image) At(x, y int) color.Color {
	a := uint8(x + y)
	b := uint8(x * y)
	return color.RGBA{a, b, 255, 255}
}

func main() {
	m := Image{}
	pic.ShowImage(m)
}
