package repsd;

import lombok.Data;


public class RepsdVo {
  private int idx;
  private String sname;
  private String title;
  private String img;
  private String etc;
  private int cnt;
  private int ref;
  private int re_step;
  private int re_level; 
  
  private String ch1; 
  private String ch2;
  
  private int sidx;
  private int pageSize;
  
public int getIdx() {
	return idx;
}
public void setIdx(int idx) {
	this.idx = idx;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public String getEtc() {
	return etc;
}
public void setEtc(String etc) {
	this.etc = etc;
}
public int getCnt() {
	return cnt;
}
public void setCnt(int cnt) {
	this.cnt = cnt;
}
public int getRef() {
	return ref;
}
public void setRef(int ref) {
	this.ref = ref;
}
public int getRe_step() {
	return re_step;
}
public void setRe_step(int re_step) {
	this.re_step = re_step;
}
public int getRe_level() {
	return re_level;
}
public void setRe_level(int re_level) {
	this.re_level = re_level;
}
public String getCh1() {
	return ch1;
}
public void setCh1(String ch1) {
	this.ch1 = ch1;
}
public String getCh2() {
	return ch2;
}
public void setCh2(String ch2) {
	this.ch2 = ch2;
}
public int getSidx() {
	return sidx;
}
public void setSidx(int sidx) {
	this.sidx = sidx;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
  
}
