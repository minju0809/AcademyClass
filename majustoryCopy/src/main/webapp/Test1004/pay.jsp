<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include/top.jsp"  />
<script>
 function  kakaoCK() {
	 quantity  = f1.quantity.value
	 amount  = f1.amount.value
	 
	 f1.total_amount.value = quantity * amount
	 
	 // alert("연산결과:" + eval(quantity * amount));
	 return true
 }
</script>

    <style>
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        .form-container label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        .form-container input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .form-container input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>

<section>
<br>
  <div> </div> 
  <div align="center"> 
   
    <form name=f1 action="${path}/PaymentServlet" method="post" 
          onSubmit="return kakaoCK()" >
          <table  border=1  width=300>
          <tr> <td>
          <label for="partner_order_id">주문번호</label>
          </td> <td>
           &emsp; <input type="text" id="partner_order_id" name="partner_order_id" > 
		  </td></tr>
		  <tr> <td>
            <label for="partner_user_id">사용자ID</label>
           </td> <td> 
           &emsp; <input type="text" id="partner_user_id" name="partner_user_id"><br>
		  </td></tr>
		  <tr> <td>
            <label for="item_name">품명</label>
             </td> <td> 
           &emsp; <input type="text" id="item_name" name="item_name"><br>
		  </td></tr>
		  <tr> <td>
            <label for="quantity">수량</label>
             </td> <td> 
           &emsp; <input type="text" id="quantity" name="quantity"><br>
		  </td></tr>
		  <tr> <td>
            <label for="amount">가격</label>
             </td> <td> 
          &emsp;  <input type="text" id="amount" name="amount"><br>
		  </td></tr>
		  <tr> <td>
            <label for="total_amount">총가격</label>
             </td> <td> 
           &emsp; <input type="text" id="total_amount" name="total_amount"><br>
		  </td></tr>
		  <tr> <td colspan=2  align="center">
            <input type="submit" value="카카오페이로 결제하기">
            </table>
    </form>  
 
  </div> 
<br> 
</section>
<jsp:include page="/include/bottom.jsp"  />