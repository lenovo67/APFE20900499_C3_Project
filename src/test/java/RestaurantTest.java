import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    //
    @BeforeEach
    public void setup() {
        LocalTime openingTime = LocalTime.parse("09:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "BLR", openingTime, closingTime);
        restaurant.addToMenu("Soup", 20);
        restaurant.addToMenu("Tea", 40);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant restaurantTiming=Mockito.spy(restaurant);
        Mockito.when(restaurantTiming.getSystemTime()).thenReturn(LocalTime.parse("13:00:00"));
        assertTrue(restaurantTiming.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurantTiming=Mockito.spy(restaurant);
        Mockito.when(restaurantTiming.getSystemTime()).thenReturn(LocalTime.parse("07:00:00"));
        assertFalse(restaurantTiming.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


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
    @Test
    public void test_sum_of_all_the_selected_items_should_pass() {
        List<Item> items = new ArrayList<>();
        items.add( new Item( "Mushroom", 30 ) );
        items.add( new Item( "Rice", 40 ) );
        items.add( new Item( "Chapati", 40 ) );
        double price = restaurant.sumOfTotalBill( items );
        Assertions.assertEquals( 110, price );
    }

        @Test
        public void test_sum_of_all_the_selected_items_should_fail(){
        List<Item> items = new ArrayList<>();
        items.add(new Item( "Mushroom", 30 )  );
        items.add(new Item( "Rice", 40 )  );
        items.add(new Item( "Chapati", 40 )  );
        double price = restaurant.sumOfTotalBill( items );
        Assertions.assertEquals( 11, price );
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}