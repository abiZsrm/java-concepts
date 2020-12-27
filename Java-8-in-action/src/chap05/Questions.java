package chap05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Questions
{
   public static int compare(Transaction t1, Transaction t2)
   {
      int diff = t1.getValue() - t2.getValue(); 
      if(diff == 0)
      {
         return 0; 
      }
      else if(diff > 0)
      {
         return 1; 
      }
      else
      {
         return -1; 
      }
   }
   
   public static void main(String[] args)
   {
      Trader raoul = new Trader("Raoul", "Cambridge"); 
      Trader mario = new Trader("Mario", "Milan"); 
      Trader alan = new Trader("Alan", "Cambridge"); 
      Trader brian = new Trader("Brian", "Cambridge"); 
      
      List<Transaction> transactions = Arrays.asList(
                                                      new Transaction(brian, 2011, 300), 
                                                      new Transaction(raoul, 2012, 1000),
                                                      new Transaction(raoul, 2011, 400), 
                                                      new Transaction(mario, 2012, 710), 
                                                      new Transaction(alan, 2012, 950)
                                                     ); 
      // Q1. Find all transactions in the year 2011 and sort them by value (small to high). 
      List<Transaction> q1 = transactions.stream()
                                          .filter((Transaction t) -> t.getYear() == 2011)
                                          .sorted(Questions::compare)
                                          .collect(Collectors.toList());
      System.out.println("\nQ1. Printing all transactions in the year 2011 and sorted by value");
      q1.forEach(System.out::println);
      
      // Q2. What are all the unique cities where the traders work? 
      List<String> q2 = transactions.stream()
                                    .map((Transaction t) -> t.getTrader().getCity())
                                    .distinct()
                                    .collect(Collectors.toList());
      
      System.out.println("\nQ2. Printing all Unique Cities");
      q2.forEach(System.out::println); 
      
      // Q3. Find all traders from Cambridge and sort them by name.
      System.out.println("\nQ3. Sorted traders by name: ");
      transactions.stream()
                  .filter((t) -> {return t.getTrader().getCity().equals("Cambridge"); })
                  .map((t) -> t.getTrader().getName())
                  .distinct()
                  .sorted()
                  .forEach(System.out::println);
      
      // Q4. Return a string of all traders names sorted alphabetically. 
      System.out.println("\nQ4. All Trader names sorted alphabetically");
      transactions.stream()
                  .map((t)-> t.getTrader().getName())
                  .distinct()
                  .sorted()
                  .reduce("", (s1, s2) -> s1 + s2); 
      
      // Q5. Are any traders based in milan?
      System.out.println("\nQ5. Are any traders based in milan? ");
      System.out.println("Answer: " + (transactions.stream()
                  .anyMatch((Transaction t) -> t.getTrader().getCity().equals("Milan")))); 
      
      // Q6. Print all transaction values from the traders living in Cambridge. 
      System.out.println("\nQ6. Print all transaction values from the traders living in Cambridge");
      transactions.stream()
                  .filter((Transaction t) -> t.getTrader().getCity().equals("Cambridge"))
                  .forEach((t) -> System.out.println(t.getValue()));
      
      // Q7. What is the highest value of all the transactions? 
      System.out.println("\nQ7. What is the highest value of all the transactions? ");
      System.out.println(transactions.stream()
               .mapToInt(Transaction::getValue)
               .max()
               .getAsInt()); 
      
      // Q8. Find the transaction with the smallest value.
      System.out.println("\nQ8. Find the transaction with the smallest value: ");
      System.out.println(transactions.stream()
               .map(Transaction::getValue)
               .sorted()
               .collect(Collectors.toList())
               .get(0)); 
   }
}
