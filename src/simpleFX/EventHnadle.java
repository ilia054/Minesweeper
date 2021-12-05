package simpleFX;

import javafx.event.Event;

public interface EventHnadle <T extends Event>{
	void handle(T event);

}
