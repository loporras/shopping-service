package academy.digitallab.store.shopping.controller;

import academy.digitallab.store.shopping.entity.Invoice;
import academy.digitallab.store.shopping.messages.ErrorMessages;
import academy.digitallab.store.shopping.service.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.EntityResponse;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


    @GetMapping
    public ResponseEntity <List<Invoice>> findAllInvoice(){
        log.info("InvoiceController --> FindAllInvoice");
        List<Invoice> invoices = invoiceService.findAllInvoice();
        if(invoices.isEmpty()){
            log.error("InvoiceController --> FindAllInvoice --> not content");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(invoices);

    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable("id") Long id){
        log.info("Find invoice by id: "+ id);
        Invoice invoice = invoiceService.findById(id);
        if(invoice == null){
            log.error("Invoice with id : "+id + " not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);

    }


    @PostMapping

    public ResponseEntity<Invoice> createdInvoice(@Valid @RequestBody Invoice invoice,BindingResult result){
        log.info("InvoiceController  --> createdInvoice --> start ");
        if(result.hasErrors()){
            String messagesError = this.formatMessages(result);
            log.error("InvoiceController --> createdInvoice --> Error: "+ messagesError);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messagesError);
        }
        Invoice invoiceDB = invoiceService.createdInvoice(invoice);
        if(invoiceDB==null){
            log.error("InvoiceController --> createdInvoice --> invoiced null: "+invoiceDB);
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDB);

    }

    @PutMapping("/{id}")
    public ResponseEntity <Invoice> updateInvoice(@RequestBody Invoice invoice, @PathVariable("id") Long id){
        log.info("InvoiceController  --> updateInvoice --> start --> id : " +id);
        invoice.setId(id);
        Invoice invoiceDB = invoiceService.updateInvoice(invoice);
        if(invoiceDB==null){
            log.error("InvoiceController  --> updateInvoice --> error update invoice id :"+id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoiceDB);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") Long id){
        log.info("InvoiceController  --> deleteInvoice --> start --> id : " +id);
        Invoice invoiceDB = invoiceService.deletedInvoice(id);
        if(invoiceDB==null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoiceDB);
    }

    public String formatMessages(BindingResult result){

        List<Map<String, String>> errors= result.getFieldErrors().stream()
                .map( err -> {
                            Map <String, String> e = new HashMap<>();
                            e.put(err.getField(), err.getDefaultMessage());
                            return e;
                        }
                ).collect(Collectors.toList());

        ErrorMessages errorMessages = ErrorMessages.builder()
                .code("01")
                .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString ="";
        try{
            jsonString = mapper.writeValueAsString(errorMessages);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }
}
