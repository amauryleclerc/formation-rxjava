package fr.sii.rxjava.exercice;

import fr.sii.rxjava.util.App;
import fr.sii.rxjava.util.Inputs;
import fr.sii.rxjava.util.Services;
import fr.sii.rxjava.util.cmds.Command;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import org.jetbrains.annotations.Contract;

import java.util.List;

import static fr.sii.rxjava.util.Cmd.addLine;
import static fr.sii.rxjava.util.MainApp.startApp;
import static java.util.Collections.singletonList;

public class Ex060_ClickPolyLine implements App {

    public static void main(String... args) {
        startApp(new Ex060_ClickPolyLine());
    }

    @Override
    @Contract(pure = true)
    public Observable<Command> commands(Inputs in, Services services, Scheduler scheduler) {
        // return Observable.never();

        return in.mouseLeftClickCount()
                .withLatestFrom(in.mouseXY(), (c, p) -> p)
                .buffer(2, 1)
                .map(pts -> addLine(pts.get(0), pts.get(1)));
    }

    @Override
    public List<String> description() {
        return singletonList("Clickez pour former une ligne brisée...");
    }
}