package academy.digitallab.store.shopping.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.function.DoubleConsumer;

@Entity(name="InvoiceItem")
@Data
@Table(name="tbl_invoice_items")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //private Invoice invoice;

    @Positive(message = "La cantidad tiene que ser mayor a cero")
    private Double quantity;

    private Double price;

    @Column(name="product_id")
    private Long productId;

    @Transient
    private Double subTotal;

    public Double getSubTotal(){
        if(this.price >0 && this.quantity >0){
            return this.quantity * this.price;
        }
        else{
            return (double) 0;
        }
    }

}
