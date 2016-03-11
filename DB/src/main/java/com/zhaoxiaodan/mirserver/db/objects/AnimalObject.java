package com.zhaoxiaodan.mirserver.db.objects;

import com.zhaoxiaodan.mirserver.db.entities.Config;
import com.zhaoxiaodan.mirserver.db.entities.Player;
import com.zhaoxiaodan.mirserver.db.types.Direction;
import com.zhaoxiaodan.mirserver.db.types.MapPoint;
import com.zhaoxiaodan.mirserver.gameserver.engine.MapEngine;
import com.zhaoxiaodan.mirserver.network.Protocol;
import com.zhaoxiaodan.mirserver.network.packets.ServerPacket;

import java.util.List;

public abstract class AnimalObject extends BaseObject {

	public int hp;
	public int maxHp;
	public boolean isAlive = true;

	public boolean hit(Direction direction) {

		broadcast(new ServerPacket(this.inGameId, Protocol.SM_HIT, this.currMapPoint.x, this.currMapPoint.y, (short) direction.ordinal()));
		return true;
	}

	public void damage(AnimalObject source, int power) {
		if (!isAlive || this.hp <= 0)
			return;

		int defend = getDefend();
		int damage = power - defend;
		damage = damage > 0 ? damage : 1;

		this.hp = (this.hp - damage);
		if (this.hp <= 0) {
			this.beKilled();
			source.kill(this);
			return;
		} else {
			broadcast(new ServerPacket.Struck(this.inGameId, this.hp, this.maxHp, damage));
			return;
		}
	}

	public void beKilled() {
		this.isAlive = false;
		broadcast(new ServerPacket(this.inGameId, Protocol.SM_DEATH, this.currMapPoint.x, this.currMapPoint.y, (short) direction.ordinal()));
	}

	public boolean walk(Direction direction) {
		if (this.move(direction, (short) 1)) {
			broadcast(new ServerPacket.Action(Protocol.SM_WALK, this));
			return true;
		} else
			return false;
	}

	public boolean run(Direction direction) {
		if (this.move(direction, (short) 2)) {
			broadcast(new ServerPacket.Action(Protocol.SM_RUN, this));
			return true;
		} else
			return false;
	}

	public boolean turn(Direction direction) {
		this.direction = direction;
		broadcast(new ServerPacket.Turn(this));
		return true;
	}

	public void broadcast(ServerPacket serverPacket) {
		for (BaseObject object : this.objectsInView.values()) {
			if (object instanceof Player)
				((Player) object).receive(serverPacket);
		}
	}


	public boolean move(Direction direction, short distance) {
		this.direction = direction;
		MapEngine.MapInfo mapInfo = MapEngine.getMapInfo(currMapPoint.mapId);

		MapPoint fromPoint = this.currMapPoint.clone();

		if (mapInfo.getObjectsOnLine(fromPoint, direction, 1, distance).size() > 0)
			return false;

		MapPoint toPoint = this.currMapPoint.clone();
		toPoint.move(direction, distance);
		mapInfo.objectMove(this, fromPoint, toPoint);

		//TODO 这个地方有优化的空间, 没必要整个屏幕扫描, 扫描边缘就可以了
		List<BaseObject> objectsInView = mapInfo.getObjects(
				toPoint.x - Config.DEFAULT_VIEW_DISTANCE,
				toPoint.x + Config.DEFAULT_VIEW_DISTANCE,
				toPoint.y - Config.DEFAULT_VIEW_DISTANCE,
				toPoint.y + Config.DEFAULT_VIEW_DISTANCE
		);

		for (BaseObject object : objectsInView) {
			this.see(object);
			object.see(this);
		}

		for (BaseObject object : this.objectsInView.values()) {
			if (object.currMapPoint == null
					|| (!toPoint.mapId.equals(object.currMapPoint.mapId))
					|| Math.abs(object.currMapPoint.x - toPoint.x) > Config.DEFAULT_VIEW_DISTANCE
					|| Math.abs(object.currMapPoint.y - toPoint.y) > Config.DEFAULT_VIEW_DISTANCE
					) {
				this.lose(object);
				object.lose(this);
			}
		}

		this.currMapPoint = toPoint;

		return true;
	}


	public abstract int getPower();

	public abstract int getDefend();

	public abstract void kill(AnimalObject animalObject);
}