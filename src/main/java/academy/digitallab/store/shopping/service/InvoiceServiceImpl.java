package academy.digitallab.store.shopping.service;

import academy.digitallab.store.shopping.entity.Invoice;
import academy.digitallab.store.shopping.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }


    @Override
    public Invoice findByNumberInvoice(String numberInvoice) {
        return  invoiceRepository.findByNumberInvoice(numberInvoice);
    }

    @Override
    public List<Invoice> findByCustomerId(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }

    @Override
    public Invoice createdInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if (invoiceDB == null){
            invoice.setState("CREATED");
            return invoiceRepository.save(invoice);
        }
        else return null;
    }

    @Override
    public Invoice deletedInvoice(Long id) {
        Invoice invoiceDB = findById(id);
        if(invoiceDB== null){
            return null;
        }
        else{
            invoiceDB.setState("DELETED");
            return invoiceRepository.save(invoiceDB);
        }

    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {

        Invoice invoiceDB =  invoiceRepository.findById(invoice.getId()).orElse(null);
        if(invoiceDB==null){
            return null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setItems(invoice.getItems());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.setDescription(invoice.getDescription());
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }
}
