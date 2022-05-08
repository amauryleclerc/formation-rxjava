package fr.sii.rxjava.operator.grouping;

import rx.Observable;

import static fr.sii.rxjava.util.rxplayground.GuiRx.createColdIntsObservable;
import static fr.sii.rxjava.util.rxplayground.GuiRx.createObserver;
import static java.awt.Color.*;

public class WindowWithOpenAndClosing {

    public static void main(String... args) {
        Observable<Integer> ints1 = createColdIntsObservable(10, 10, "Ints 1", RED, 0, 5);

        Observable<Integer> opening = createColdIntsObservable(200, 10, "Opening", ORANGE, 8, 8);
        Observable<Integer> closing = createColdIntsObservable(400, 10, "Closing", MAGENTA, 10, 10);

        Observable<String> windowed = ints1
                .window(opening, __ -> closing)
                .flatMap(windowValues -> windowValues.scan("Open", (acc, v) -> acc + "+" + v));

        createObserver(10, 350, "Observer", GREEN, windowed);
    }
}
