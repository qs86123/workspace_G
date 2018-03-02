package gotestttt

import(
	"testing"
	"../gotest"
)
/**
用命令行跑不成功，ide跑能成功
 */
func Test_Division_1(t *testing.T) {
	if i, e := gotest.Division(6, 2); i != 3 || e != nil { //try a unit test on function
		t.Error("除法函数测试没通过") // 如果不是如预期的那么就报错
	} else {
		t.Log("第一个测试通过了") //记录一些你期望记录的信息
	}
}

//func Test_Division_2(t *testing.T) {
//	t.Error("就是不通过")
//}

/**
压力测试
 */
func Benchmark_Divisionwe(b *testing.B) {
	for i := 0; i < b.N; i++ { //use b.N for looping
		gotest.Division(4, 5)
	}
}

func Benchmark_TimeConsumingFunction(b *testing.B) {
	b.StopTimer() //调用该函数停止压力测试的时间计数

	//做一些初始化的工作,例如读取文件数据,数据库连接之类的,
	//这样这些时间不影响我们测试函数本身的性能

	b.StartTimer() //重新开始时间
	for i := 0; i < b.N; i++ {
		gotest.Division(4, 5)
	}
}