package main

import (
	_ "github.com/go-sql-driver/mysql"
	_ "github.com/astaxie/beego/orm"
	"database/sql"
	"fmt"
)

func main() {
	db, err := sql.Open("mysql", "root:123456@/dx?charset=utf8")
	checkErr(err)

	//插入数据
	stmt, err := db.Prepare("INSERT test_go SET `name`=?,`age`=?")
	checkErr(err)

	res, err := stmt.Exec("wangtao", "23")
	checkErr(err)

	id, err := res.LastInsertId()
	checkErr(err)

	fmt.Println(id)
	//更新数据
	stmt, err = db.Prepare("update test_go set `name`=? where id=?")
	checkErr(err)

	res, err = stmt.Exec("wtchange", id)
	checkErr(err)

	affect, err := res.RowsAffected()
	checkErr(err)

	fmt.Println(affect)

	//查询数据
	rows, err := db.Query("SELECT * FROM test_go")
	checkErr(err)

	for rows.Next() {
		var id string
		var name string
		var age string
		err = rows.Scan(&id, &name, &age)
		checkErr(err)
		fmt.Println(id)
		fmt.Println(name)
		fmt.Println(age)
	}

	//删除数据
	stmt, err = db.Prepare("delete from test_go where id=?")
	checkErr(err)

	res, err = stmt.Exec(id)
	checkErr(err)

	affect, err = res.RowsAffected()
	checkErr(err)

	fmt.Println(affect)

	db.Close()

}

func checkErr(err error) {
	if err != nil {
		panic(err)
	}
}
