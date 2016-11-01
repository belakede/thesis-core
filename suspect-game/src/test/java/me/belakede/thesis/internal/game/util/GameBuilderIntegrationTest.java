package me.belakede.thesis.internal.game.util;

import me.belakede.thesis.game.Game;
import me.belakede.thesis.game.equipment.BoardType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GameBuilderIntegrationTest {

    @Test
    public void testCanCreateAValidDefaultGameWithFourPlayer() throws Exception {
        Game actual = GameBuilder.create()
                .boardType(BoardType.DEFAULT)
                .mystery()
                .players(4)
                .positions()
                .build();
        assertFalse(actual.isGameEnded());
        assertEquals(BoardType.DEFAULT, actual.getBoard().getBoardType());
        assertEquals(Figurines.getNumberOfFigurines(), actual.getPositions().size());
    }

}