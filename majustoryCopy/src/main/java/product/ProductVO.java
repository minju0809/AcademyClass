package product;

import lombok.Data;

@Data
public class ProductVO {
  private String	pid;
  private String	pname;
  private int       pprice;
  private int	    pbaesongbi;
  private String    pdesc;
  private String	pimg;
}
