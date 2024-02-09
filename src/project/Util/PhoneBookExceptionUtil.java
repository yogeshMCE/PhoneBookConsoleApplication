package project.Util;

public class PhoneBookExceptionUtil {
    public boolean ValidateAddCommandLength(int AddCommandLength){
        return (AddCommandLength>=3 && AddCommandLength<=5);
    }
    public boolean ValidateContactNUmber(String CompleteContactNumber){
         String [] ContactNumArr= CompleteContactNumber.split("-");
         int len= ContactNumArr.length;
         String ContactNum= ContactNumArr[len-1];
         char [] ContactNumChar= ContactNum.toCharArray();
          for( char ch: ContactNumChar){
              if(!Character.isDigit(ch)){
                  return false;
              }
          }
          return true;

    }
    public boolean ValidateGetCommandLength(int AddCommandLength){
        return (AddCommandLength==2);
    }
    public boolean ValidateUpdateCommandLength(int AddCommandLength){
        return (AddCommandLength==4);
    }
    public boolean ValidateUpdateField(String UpdateField){
        return UpdateField.equalsIgnoreCase("first-name")
                || UpdateField.equalsIgnoreCase("middle-name")
                || UpdateField.equalsIgnoreCase("last-name")
                ||UpdateField.equalsIgnoreCase("contact-number");
    }
    public boolean DeleteCommandLength(int DeleteCommandLength){
        return DeleteCommandLength==2;
    }
    public boolean SortCommandLength(int DeleteCommandLength){
        return DeleteCommandLength==3;
    }
    public boolean ValidateSortField(String UpdateField){
        return UpdateField.equalsIgnoreCase("first-name")
                || UpdateField.equalsIgnoreCase("middle-name")
                || UpdateField.equalsIgnoreCase("last-name");
    }
    public boolean ValidateOrderForSort(String Order){
        return (Order.equals("a-z") || Order.equals("z-a"));
    }

}
