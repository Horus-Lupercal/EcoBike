package com.task.ecobike.dto;

import java.util.List;

import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;

public class ProductsDTO {
	
	private List<Speedelec> speedelec;
	private List<FoldingBike> foldingBikes;
	private List<EBike> eBikes;
	
	public List<Speedelec> getSpeedelec() {
		return speedelec;
	}
	public void setSpeedelec(List<Speedelec> speedelec) {
		this.speedelec = speedelec;
	}
	
	public List<FoldingBike> getFoldingBikes() {
		return foldingBikes;
	}
	public void setFoldingBikes(List<FoldingBike> foldingBikes) {
		this.foldingBikes = foldingBikes;
	}
	
	public List<EBike> geteBikes() {
		return eBikes;
	}
	public void seteBikes(List<EBike> eBikes) {
		this.eBikes = eBikes;
	}
	
	@Override
	public String toString() {
		return "ProductsDTO [speedelec=" + speedelec + ", foldingBikes=" + foldingBikes + ", eBikes=" + eBikes + "]";
	}

	
}
