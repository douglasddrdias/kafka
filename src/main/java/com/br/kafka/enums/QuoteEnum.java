package com.br.kafka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum QuoteEnum {
    OBIWAN(10, List.of(
            "You must do what you feel is right, of course. \n-Obi-Wan Kenobi",
            "Mos Eisley Spaceport. You will never find a more wretched hive of scum and villainy. We must be cautious. \n-Obi-Wan Kenobi",
            "Your eyes can deceive you. Don't trust them. \n-Obi-Wan Kenobi",
            "Remember... the Force will be with you, always. \n-Obi-Wan Kenobi",
            "In my experience, there is no such thing as luck. \n-Obi-Wan Kenobi",
            "These aren't the droids you're looking for. \n-Obi-Wan Kenobi",
            "I felt a great disturbance in the Force, as if millions of voices suddenly cried out in terror and were suddenly silenced. \n-Obi-Wan Kenobi",
            "Use the Force, Luke.\n-Obi-Wan Kenobi",
            "You can't win, Darth. If you strike me down, I shall become more powerful than you could possibly imagine. \n-Obi-Wan Kenobi",
            "That's no moon. It's a space station. \n-Obi-Wan Kenobi",
            "Luke! Don't give in to hate. That leads to the Dark Side. \n-Obi-Wan Kenobi",
            "Who's the more foolish, the fool or the fool who follows him? \n-Obi-Wan Kenobi",
            "And these blast points, too accurate for Sandpeople. Only Imperial Stormtroopers are so precise. \n-Obi-Wan Kenobi"
    )),
    DATH_VADER(4, List.of(
            "I find your lack of faith disturbing. \n-Darth Vader",
            "The circle is now complete. When I left you, I was but the learner. Now I am the master. \n-Darth Vader",
            "The Force is with you, young Skywalker, but you are not a Jedi yet. \n-Darth Vader",
            "No. I am your father. \n-Darth Vader",
            "Impressive. Most impressive. \n-Darth Vader",
            "I am altering the deal. Pray I don't alter it any further. \n-Darth Vader",
            "You underestimate the power of the Dark Side. If you will not fight, then you will meet your destiny. \n-Darth Vader",
            "You have failed me for the last time, Admiral! \n-Darth Vader",
            "The Force is strong with this one. \n-Darth Vader",
            "The ability to destroy a planet is insignificant next to the power of the Force. \n-Darth Vader"
    )),
    YODA(20, List.of(
            "Do. Or do not. There is no try. \n-Yoda",
            "You must unlearn what you have learned. \n-Yoda",
            "When nine hundred years old you reach, look as good you will not. \n-Yoda",
            "Truly wonderful, the mind of a child is. \n-Yoda",
            "A Jedi uses the Force for knowledge and defense, never for attack. \n-Yoda",
            "Adventure. Excitement. A Jedi craves not these things. \n-Yoda",
            "Size matters not. Judge me by my size, do you? \n-Yoda",
            "Fear is the path to the dark side…fear leads to anger…anger leads to hate…hate leads to suffering. \n-Yoda",
            "Wars not make one great. \n-Yoda",
            "Luminous beings are we…not this crude matter. \n-Yoda",
            "Difficult to see. Always in motion is the future. \n-Yoda",
            "Control, control, you must learn control! \n-Yoda"
    )),
    LUKE(1, List.of(
            "I'll never turn to the Dark Side. You've failed, your highness. I am a Jedi, like my father before me. \n-Luke Skywalker",
            "If there's a bright center to the universe, you're on the planet that it's farthest from. \n-Luke Skywalker",
            "I'm Luke Skywalker. I'm here to rescue you. \n-Luke Skywalker",
            "The Force is strong in my family. My father has it. I have it. My sister has it. You have that power, too. \n-Luke Skywalker",
            "But I was going into Tosche Station to pick up some power converters! \n-Luke Skywalker"
    )),
    HAN_SOLO(14, List.of(
            "Chewie...we're home. \n-Han Solo",
            "Never tell me the odds. \n-Han Solo",
            "Women always figure out the truth. Always. \n-Han Solo",
            "Fast ship? You've never heard of the Millennium Falcon? It's the ship that made the Kessel Run in less than twelve parsecs. \n-Han Solo",
            "Great shot, kid, that was one in a million! \n-Han Solo",
            "Boring conversation anyway. LUKE, WE'RE GONNA HAVE COMPANY! \n-Han Solo",
            "No reward is worth this. \n-Han Solo",
            "Wonderful girl. Either I'm going to kill her or I'm beginning to like her. \n-Han Solo",
            "Look, Your Worshipfulness, let's get one thing straight. I take orders from just one person: me. \n-Han Solo"
    )),
    LEIA(6, List.of(
            "Help me, Obi-Wan Kenobi. You're my only hope. \n-Princess Leia Organa",
            "Aren't you a little short for a stormtrooper? \n-Princess Leia Organa",
            "Someone has to save our skins. Into the garbage chute, fly boy. \n-Princess Leia Organa",
            "Will someone get this big walking carpet out of my way? \n-Leia Organa",
            "You needn't worry about your reward. If money is all that you love, then that's what you'll receive. \n-Leia Organa"
    ));

    private final int id;
    private final List<String> quotes;

    public static Optional<QuoteEnum> getById(int id) {
        return Stream.of(values())
                .filter(q -> q.id == id)
                .findFirst();
    }

}
