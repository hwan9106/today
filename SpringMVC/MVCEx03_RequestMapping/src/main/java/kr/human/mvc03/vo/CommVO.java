package kr.human.mvc03.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommVO {
	private int p=1;
	private int s=10;
	private int b=10;
	private int idx=-1;
	private int m=0;
	
	@XmlElement
	private int currentPage=1;
	@XmlElement
	private int pageSize=10;
	@XmlElement
	private int blockSize=10;
	@XmlElement
	private int mode=0;
	
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
		currentPage = this.p;
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
		this.pageSize = this.s;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
		this.blockSize = this.b;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
		this.mode = this.m;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	@Override
	public String toString() {
		return "CommVO [p=" + p + ", s=" + s + ", b=" + b + ", idx=" + idx + ", m=" + m + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", blockSize=" + blockSize + ", mode=" + mode + "]";
	}
	
}
