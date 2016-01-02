package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartPoint implements Serializable {
	public ChartPoint(){
		this.show = true;
		this.focus = new ChartPointFocus();
		this.select = new ChartPointSelect();
		this.r = 2.5;
		this.focus.expand.r = this.r * 1.75;
		this.select.r = this.r * 4;
	}
	
	private Boolean show;
	private Double r;
	private ChartPointFocus focus;
	private ChartPointSelect select;
	
	public Boolean getShow() {
		return show;
	}
	public void setShow(Boolean show) {
		this.show = show;
	}
	public Double getR() {
		return r;
	}
	public void setR(Double r) {
		this.r = r;
	}
	public ChartPointFocus getFocus() {
		return focus;
	}
	public void setFocus(ChartPointFocus focus) {
		this.focus = focus;
	}
	public ChartPointSelect getSelect() {
		return select;
	}
	public void setSelect(ChartPointSelect select) {
		this.select = select;
	}
	
	

	public class ChartPointSelect{
		public ChartPointSelect(){
			
		}
		
		private Double r;

		public Double getR() {
			return r;
		}
		public void setR(Double r) {
			this.r = r;
		}
	}
	
	public class ChartPointFocus{
		public ChartPointFocus(){
			this.expand = new ChartPointExpand();
		}
		
		private ChartPointExpand expand;
		
		public ChartPointExpand getExpand() {
			return expand;
		}
		public void setExpand(ChartPointExpand expand) {
			this.expand = expand;
		}

		public class ChartPointExpand{
			public ChartPointExpand(){
				this.enabled = true;
			}
			private Boolean enabled;
			private Double r;
			
			public Boolean getEnabled() {
				return enabled;
			}
			public void setEnabled(Boolean enabled) {
				this.enabled = enabled;
			}
			public Double getR() {
				return r;
			}
			public void setR(Double r) {
				this.r = r;
			}
		}
	}
}
