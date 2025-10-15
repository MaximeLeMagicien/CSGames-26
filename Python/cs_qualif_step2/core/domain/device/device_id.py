import hashlib
import uuid
from dataclasses import dataclass

@dataclass(order=True)
class DeviceId:
    value: str

    @classmethod
    def from_string(cls, device_id: str) -> "DeviceId":
        return DeviceId(device_id)

    @classmethod
    def generate(cls) -> "DeviceId":
        return DeviceId(hashlib.sha256(uuid.uuid4().bytes).hexdigest())

    def __eq__(self, other):
        if isinstance(other, DeviceId):
            return self.value == other.value
        return False

    def __str__(self):
        return str(self.value)
