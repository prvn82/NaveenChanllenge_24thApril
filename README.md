# NaveenChanllenge_24thApril
Code URl: https://www.youtube.com/watch?v=n0_Lj5YJvgQ

## TestCases Covered
* Testing URL: https://www.makemytrip.com/
* Enter City as Delhi and Bangalore
* Select date as current date and Arrival after 7 days
* Passanger count is 1 ( 1 by default )
* Validate number of flight listed without any filter
* Apply Non-Stop filter
* Validate number of flights lister after filter applied
* Apply One-stop filter
* Validate number of flights lister after filter applied
* Make random selection of departure flight and arrival flight ( limit first 10 row )
* Validate total fare and fare of selected flights

## Advance feature implenented here
1. Extent reporting 3 with test Steps logs, Failed Screen shot, Exception details and System info.
2. Used advanced Dynamnic xpath to select calender date instead of regular for loop to handle calender. 
3. Testng Report with Reporting logs. 

## limitation of design
* Date gap is Set for only 7 days ( Hard coded)
* Static Thead.sleep method is used once to handle highly dynamic web element ( Not recommed for selenium )


