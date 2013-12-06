package legacy;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

public class InnTest {
    Inn inn = new Inn();

    @Test
    public void should_list_items() {
        assertThat(extractProperty("name").from(inn.getItems())).containsExactly("+5 Dexterity Vest", "Aged Brie", "Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros", "Backstage passes to a TAFKAL80ETC concert", "Conjured Mana Cake");
        assertThat(extractProperty("quality").from(inn.getItems())).containsExactly(20, 0, 7, 80, 20, 6);
        assertThat(extractProperty("sellIn").from(inn.getItems())).containsExactly(10, 2, 5, 0, 15, 3);
    }

    @Test
    public void should_update_items() {
        inn.updateQuality();

        assertThat(extractProperty("quality").from(inn.getItems())).containsExactly(19, 1, 6, 80, 21, 5);
        assertThat(extractProperty("sellIn").from(inn.getItems())).containsExactly(9, 1, 4, 0, 14, 2);
    }

    @Test
    public void should_update_items_twice() {
        inn.updateQuality();
        inn.updateQuality();

        assertThat(extractProperty("quality").from(inn.getItems())).containsExactly(18, 2, 5, 80, 22, 4);
        assertThat(extractProperty("sellIn").from(inn.getItems())).containsExactly(8, 0, 3, 0, 13, 1);
    }

    @Test
    public void should_update_items_a_lot() {
        for (int day = 0; day < 50; day++) {
            inn.updateQuality();
        }

        assertThat(extractProperty("quality").from(inn.getItems())).containsExactly(0, 50, 0, 80, 0, 0);
        assertThat(extractProperty("sellIn").from(inn.getItems())).containsExactly(-40, -48, -45, 0, -35, -47);
    }

    /*@Test
    public void should_test_against_legacy_code() {
        LegacyInn legacyInn = new LegacyInn();

        for (int day = 0; day < 100; day++) {
            List<Item> items = inn.getItems();
            List<Item> legacyItems = legacyInn.getItems();

            for (int i = 0; i < legacyItems.size(); i++) {
                Item item = items.get(i);
                Item legacyItem = legacyItems.get(i);

                assertThat(item.getName()).isEqualTo(legacyItem.getName());
                assertThat(item.getQuality()).isEqualTo(legacyItem.getQuality());
                assertThat(item.getSellIn()).isEqualTo(legacyItem.getSellIn());
            }

            inn.updateQuality();
            legacyInn.updateQuality();
        }
    }*/

}
