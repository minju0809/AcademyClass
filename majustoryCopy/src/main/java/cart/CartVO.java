package cart;

import lombok.Data;

@Data
public class CartVO {
   private  String cart_id;
   private  String mid;
   private  String pid;
   private  int  amount;   
}
