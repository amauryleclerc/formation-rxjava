package fr.sii.rxjava.exercice;

import fr.sii.rxjava.util.*;
import fr.sii.rxjava.util.cmds.Command;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import org.jetbrains.annotations.Contract;

import java.util.List;

import static fr.sii.rxjava.exercice.Ex250_ColoredLetters.endTyping;
import static fr.sii.rxjava.exercice.Ex250_ColoredLetters.startTyping;
import static fr.sii.rxjava.util.Cmd.addText;
import static fr.sii.rxjava.util.Cmd.uniq;
import static fr.sii.rxjava.util.MainApp.startApp;
import static io.reactivex.rxjava3.core.Observable.*;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Ex251_ColoredLetters2 implements App {

    public static void main(String... args) {
        startApp(new Ex251_ColoredLetters2());
    }

    @Contract(pure = true)
    public Observable<Command> commands(Inputs in, Services services, Scheduler scheduler) {
        

        return concat(in.mouseLeftClickCount()
                .withLatestFrom(in.mouseXY(), (c, p) -> p)
                .zipWith(fromArray(Couleur.values()).repeat(), T2::t2)
                .groupJoin(in.keys(),
                        pac -> in.keys().timeout(2, SECONDS).ignoreElements().toObservable().onErrorResumeNext(t ->Observable.empty()),
                        c -> empty(),
                        (p_couleur, chars) -> {
                            Pt p = p_couleur._1;
                            Couleur couleur = p_couleur._2;

                            return just(startTyping(couleur))
                                    .concatWith(chars
                                            .scan("", (acc, chaR) -> acc + chaR)
                                            .skip(1)
                                            .map(str -> uniq("" + p, addText(p, str, couleur.color))))
                                    .concatWith(just(endTyping(p_couleur._2)));
                        }));
    }

    @Override
    public List<String> description() {
        return singletonList("Clickez puis tapez, attendre un peu pour terminer la saisie...");
    }
}