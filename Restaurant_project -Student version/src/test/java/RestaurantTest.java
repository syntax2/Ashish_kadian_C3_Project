import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void beforeEach(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        LocalTime mockedCurrentTime = LocalTime.parse("11:29:00"); // Mocked current time, lies inside restaurant timing.
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(mockedCurrentTime);

        //Calling isRestaurantOpen()
        boolean isRestaurantOpen = spiedRestaurant.isRestaurantOpen();

        //Assertion
        assertTrue(isRestaurantOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        LocalTime mockedCurrentTime = LocalTime.parse("23:29:00"); // Mocked current time, lies outside restaurant timing.
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(mockedCurrentTime);

        //Calling isRestaurantOpen()
        boolean isRestaurantOpen = spiedRestaurant.isRestaurantOpen();

        //Assertion
        assertFalse(isRestaurantOpen);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>>>>>>CALCULATE TOTAL COST<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void after_items_selected_order_cost_should_return_total_cost_of_selected_items(){
        //Adding items to the order list
        List<String> order = new ArrayList<String>();
        order.add("Sweet corn soup");
        order.add("Vegetable lasagne");

        //Placing the order and getting the order cost
        int orderTotalCost = restaurant.placeOrderAndGetCost(order);

        //Assertion
        assertEquals(388,orderTotalCost);
    }

    @Test
    public void when_no_items_selected_order_cost_should_return_zero(){
        //Creating order list, but not adding anything into the list
        List<String> order = new ArrayList<String>();

        //Placing the order and getting the order cost
        int orderTotalCost = restaurant.placeOrderAndGetCost(order);

        //Assertion
        assertEquals(0,orderTotalCost);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<CALCULATE TOTAL COST>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void checking_display_details_of_restaurant() {
        // To improve the test coverage
        restaurant.displayDetails();
    }
    
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}