package cn.mkepu.xbzs.segmenter;

import cn.mkepu.xbzs.XBZS;

public class XSegmenterFactory {

	public static XSegmenter getInstance() {
		return new XIKSegmenter();
	}

	public static XSegmenter getInstance(int type) {
		XSegmenter result = null;
		switch (type) {
		case XBZS.XIKSEG:
			result = new XIKSegmenter();
			break;
		/*
		case XBZS.XMMSEG:
			result = new XMMSegmenter();
			break;
		case XBZS.XANSEG:
			result = new XANSegmenter();
			break;
		case XBZS.XJCSEG:
			result = new XJCSegmenter();
			break;
		case XBZS.XFDSEG:
			result = new XFDSegmenter();
			break;
		*/
		}
		return result;
	}
}
