package com.example.demo1;

public abstract class Aclass{
	public int i=100;
	public String str="name";
	public String name="wangtao";
	OnInstence lisntener;
	abstract void getint(int i);
	public Aclass(){
		getint(i);
		out(name);
	}
	
	public void getstrisright(){
		boolean isright=lisntener.getstrfromotherclass(str);
		String str1=lisntener.getstr();
		System.out.println(str1+"与Aclass中的str=name相同?"+isright);
		
	}
	
//	public interface OnInstence{
//		boolean getstrfromotherclass(String str);
//		String getstr();
//	}//把接口放在其他地方也行
	public void setOnInstence(OnInstence listener){
		this.lisntener=listener;
	}
	
	public void out(String name){
		
	}
	
}
