class ConflictException(Exception):
    def __init__(self, message="Conflict in resource state"):
        self.message = message
        super().__init__(self.message)
