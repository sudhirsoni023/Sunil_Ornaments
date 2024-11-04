package com.ornaments.sunil.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sl_no;
	private String name;
	private String gstin_no;
	private String particulars;
	private String weight;
	private String rate;
	private String amount;
	@Column(unique = true)
	private String invoice_no;
	private String date;
	private String aadhar_no;
	private String pan_no;
	private String net_total;
	private String cgst;
	private String sgst;
	private String total_invoice_value_inwords;

	public Bill() {
		super();
	}

	@Override
	public String toString() {
		return "Bill [sl_no=" + sl_no + ", name=" + name + ", gstin_no=" + gstin_no + ", particulars=" + particulars
				+ ", weight=" + weight + ", rate=" + rate + ", amount=" + amount + ", invoice_no=" + invoice_no
				+ ", date=" + date + ", aadhar_no=" + aadhar_no + ", pan_no=" + pan_no + ", net_total=" + net_total
				+ ", cgst=" + cgst + ", sgst=" + sgst + ", total_invoice_value_inwords=" + total_invoice_value_inwords
				+ "]";
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGstin_no() {
		return gstin_no;
	}

	public void setGstin_no(String gstin_no) {
		this.gstin_no = gstin_no;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAadhar_no() {
		return aadhar_no;
	}

	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getNet_total() {
		return net_total;
	}

	public void setNet_total(String net_total) {
		this.net_total = net_total;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getTotal_invoice_value_inwords() {
		return total_invoice_value_inwords;
	}

	public void setTotal_invoice_value_inwords(String total_invoice_value_inwords) {
		this.total_invoice_value_inwords = total_invoice_value_inwords;
	}

}
