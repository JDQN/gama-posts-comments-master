package com.posada.santiago.gamapostsandcomments.domain.participant;

import co.com.sofka.domain.generic.EventChange;
import com.posada.santiago.gamapostsandcomments.domain.commons.values.Content;
import com.posada.santiago.gamapostsandcomments.domain.commons.values.Name;
import com.posada.santiago.gamapostsandcomments.domain.commons.values.PhotoUrl;
import com.posada.santiago.gamapostsandcomments.domain.commons.values.PostId;
import com.posada.santiago.gamapostsandcomments.domain.participant.events.EventCasted;
import com.posada.santiago.gamapostsandcomments.domain.participant.events.FavAdded;
import com.posada.santiago.gamapostsandcomments.domain.participant.events.MessageReceived;
import com.posada.santiago.gamapostsandcomments.domain.participant.events.ParticipantCreated;
import com.posada.santiago.gamapostsandcomments.domain.participant.values.DateOfEvent;
import com.posada.santiago.gamapostsandcomments.domain.participant.values.Detail;
import com.posada.santiago.gamapostsandcomments.domain.participant.values.Element;
import com.posada.santiago.gamapostsandcomments.domain.participant.values.EventId;
import com.posada.santiago.gamapostsandcomments.domain.participant.values.MessageId;
import com.posada.santiago.gamapostsandcomments.domain.participant.values.Rol;
import com.posada.santiago.gamapostsandcomments.domain.participant.values.Type;

import java.util.ArrayList;

public class ParticipantChange extends EventChange {

    public ParticipantChange(Participant participant){
        apply((ParticipantCreated event)->{
            participant.name = new Name(event.getName());
            participant.photoUrl = new PhotoUrl(event.getPhotoUrl());
            participant.rol = new Rol(event.getPhotoUrl());
            participant.favorites = new ArrayList<>();
            participant.events = new ArrayList<>();
            participant.messages = new ArrayList<>();
        });

        apply((EventCasted event)->{
            Event participantEvent = new Event(
                    EventId.of(event.getEventId()),
                    new DateOfEvent(event.getDate()),
                    new Element(event.getElement()),
                    new Type(event.getType()),
                    new Detail(event.getDetail()));
            participant.events.add(participantEvent);
        });

        apply((FavAdded event)->{
            participant.favorites.add(PostId.of(event.getPostId()));
        });

        apply((MessageReceived event)->{
            Message message = new Message(
                    MessageId.of(event.getMessageId()),
                    new Name(event.getName()),
                    new Content(event.getContent()));
            participant.messages.add(message);
        });
    }
}
