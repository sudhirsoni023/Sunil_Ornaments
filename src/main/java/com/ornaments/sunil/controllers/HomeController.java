package com.ornaments.sunil.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ornaments.sunil.Dao.BillRepository;
import com.ornaments.sunil.models.Bill;

@Controller
public class HomeController {

	@Autowired
	BillRepository billRepository;

	// For opening of home Page ie. index page
	@GetMapping("/")
	public String HomePage(Model model) {
		model.addAttribute("title", "Home- Smart Contact Manager");
		return "index";
	}

	// For opening of billing dashboard home page
	@GetMapping("/bill")
	public String BillPage(Model model) {
		model.addAttribute("title", "Billing Dashboard- Smart Contact Manager");
		return "billing/bill_dashboard";
	}

	// For opening of add-new-bill form page
	@GetMapping("/add-new-bill")
	public String AddNewBill(Model model) {
		model.addAttribute("title", "Add Bill- Smart Contact Manager");
		model.addAttribute("bill", new Bill());
		return "billing/add_new_bill";
	}

	// For opening of all bills view-page
	@GetMapping("/view-bills")
	public String ViewBills(Model model) {
		model.addAttribute("title", "View Bill- Smart Contact Manager");
		List<Bill> bills = this.billRepository.findAll();
		model.addAttribute("bills", bills);
		return "billing/view_bills";
	}

	// For opening of particular bill page with given invoice no
	@GetMapping("/view_bill/{invoice_no}")
	public String ViewBillDetails(Model model, @PathVariable("invoice_no") String invoice_no) {
		model.addAttribute("title", "View Bill-Details - Smart Contact Manager");

		Bill b = this.billRepository.findByinvoice_no(invoice_no);
		model.addAttribute("bills", b);
		return "billing/view_bill_details";
	}

	// For Deleting the given invoice no and then opening all bills view-page
	@PostMapping("/delete_bill/{invoice_no}")
	public String DeleteBillDetails(Model model, @PathVariable("invoice_no") String invoice_no) {
		model.addAttribute("title", "Delete Bill-Details - Smart Contact Manager");

		Bill b = this.billRepository.findByinvoice_no(invoice_no);
		this.billRepository.delete(b);
		List<Bill> bills = this.billRepository.findAll();
		model.addAttribute("bills", bills);
		model.addAttribute("message", "Bill of Invoice no. " + invoice_no + " Deleted Successfully");
		model.addAttribute("alert_class", "alert-success");
		return "billing/view_bills";
	}

	// For opening of update bill form page
	@PostMapping("/update_bill/{invoice_no}")
	public String UpdateBillDetails(Model model, @PathVariable("invoice_no") String invoice_no) {
		model.addAttribute("title", "Update Bill-Details - Smart Contact Manager");

		Bill b = this.billRepository.findByinvoice_no(invoice_no);
		model.addAttribute("bill", b);
		return "billing/update_bill";
	}

	// For updating the given invoice details and opening the all bills page
	@PostMapping("/process-update-bill")
	public String processUpdateBill(@ModelAttribute Bill bill, Model model,
			@RequestParam("invoice_no") String invoice_no) {
		try {
			String net_total = String.format("%.2f", Double.parseDouble(bill.getNet_total()));
			String amount = String.format("%.2f", Double.parseDouble(bill.getAmount()));
			String cgst = String.format("%.2f", Double.parseDouble(bill.getCgst()));
			String sgst = String.format("%.2f", Double.parseDouble(bill.getSgst()));
			bill.setNet_total(net_total);
			bill.setAmount(amount);
			bill.setCgst(cgst);
			bill.setSgst(sgst);
			this.billRepository.save(bill);
			List<Bill> bills = this.billRepository.findAll();
			model.addAttribute("bills", bills);
			model.addAttribute("message", "Bill of Invoice no. " + invoice_no + " Updated Successfully");
			model.addAttribute("alert_class", "alert-success");
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("message", "Something went Wrong, please try again");
			model.addAttribute("alert-class", "alert_danger");
		}
		return "billing/view_bills";
	}

	// For Adding the new bill and redirecting back to add_bill form page with
	// success message
	@PostMapping("/process-bill")
	public String processAddNewBill(@ModelAttribute Bill bill, Model model) {
		try {
			String net_total = String.format("%.2f", Double.parseDouble(bill.getNet_total()));
			String amount = String.format("%.2f", Double.parseDouble(bill.getAmount()));
			String cgst = String.format("%.2f", Double.parseDouble(bill.getCgst()));
			String sgst = String.format("%.2f", Double.parseDouble(bill.getSgst()));
			bill.setNet_total(net_total);
			bill.setAmount(amount);
			bill.setCgst(cgst);
			bill.setSgst(sgst);
			this.billRepository.save(bill);
			model.addAttribute("message", "Bill Added Successfully");
			model.addAttribute("alert_class", "alert-success");
		} catch (Exception ex) {
			if (ex.getMessage().toLowerCase().contains("duplicate")) {
				model.addAttribute("message",
						"Duplicate Entry of invoice no, please try again with different invoice no");
				model.addAttribute("alert_class", "alert-danger");
			} else {
				ex.printStackTrace();
				model.addAttribute("message", "Something went Wrong, please try again");
				model.addAttribute("alert-class", "alert_danger");
			}
		}
		return "billing/add_new_bill";
	}

	// For searching the bills with given name of customer
	@GetMapping("/search")
	public String search_Query(@RequestParam("searchQuery") String searchQuery, Model model) {
		List<Bill> bills = this.billRepository.findByNameContaining(searchQuery);
		model.addAttribute("bills", bills);
		return "billing/view_bills";
	}

	@GetMapping("/confirmDelete")
	public String confirm_Delete() {
		return "billing/confirm_delete";
	}

	@PostMapping("/Delete-Confirm")
	public String Delete_Confirm() {
		this.billRepository.deleteAll();
		return "billing/view_bills";
	}
}
