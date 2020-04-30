package academy.digitallab.store.shopping.repository;

import academy.digitallab.store.shopping.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public List<Invoice> findByCustomerId(Long Customerid);
    public Invoice findByNumberInvoice (String numberInvoice);
}
