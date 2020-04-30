package academy.digitallab.store.shopping.service;

import academy.digitallab.store.shopping.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    public List<Invoice> findAllInvoice();
    public Invoice findByNumberInvoice(String numberInvoice);
    public List<Invoice> findByCustomerId(Long customerId);
    public Invoice createdInvoice(Invoice invoice );
    public Invoice deletedInvoice(Long id);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice findById(Long id);


}
