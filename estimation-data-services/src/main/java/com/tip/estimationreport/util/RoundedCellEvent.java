package com.tip.estimationreport.util;

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPCellEvent;
import com.lowagie.text.pdf.PdfPTable;

public class RoundedCellEvent implements PdfPCellEvent {

	String cellType;
	public RoundedCellEvent(String cellType){
		this.cellType = cellType;
	}
	@Override
	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
		if(cellType.equalsIgnoreCase("TOP")){
			PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
			float llx = position.getLeft();
			float lly = position.getBottom();
			float urx = position.getRight();
			float ury = position.getTop();
			float r = 2;
			float b = 0.4477f;
			canvas.moveTo(llx, lly);
			canvas.lineTo(urx, lly);
			canvas.lineTo(urx, ury - r);
			canvas.curveTo(urx, ury - r * b, urx - r * b, ury, urx - r, ury);
			canvas.lineTo(llx + r, ury);
			canvas.curveTo(llx + r * b, ury, llx, ury - r * b, llx, ury - r);
			canvas.lineTo(llx, lly);
			//canvas.stroke();
			cell.setBorderColor(StyleUtil.TITLE_FONT_COLOR);
		}
		
	}

}
